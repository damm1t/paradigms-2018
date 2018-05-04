/**
 * Created by damm1t.
 * Date: 27.04.2018
 * Time: 1:05
 */
"use strict";

function Const(x) {
    this.value = x;
}

Const.prototype = {
    evaluate: function () {
        return this.value;
    },
    toString: function () {
        return this.value.toString();
    },
    diff: function () {
        return ZERO;
    },
    simplify: function () {
        return this;
    }
};

function Variable(varName) {
    this.ind = VARIABLES[varName];
    this.name = varName;
}

Variable.prototype = {
    evaluate: function () {
        return arguments[this.ind];
    },
    toString: function () {
        return this.name;
    },
    diff: function (varName) {
        return varName === this.name ? ONE : ZERO;
    },
    simplify: function () {
        return this;
    }
};

function operationFactory(cntOp, name, operator, diffOp) {
    function AbstractOperation() {
        this.arguments = [].slice.call(arguments);
    }

    AbstractOperation.prototype = {
        length: cntOp,
        operator: operator,
        evaluate: function () {
            var args = arguments;
            var evaluatedOperands = this.arguments.map(function (value) {
                return value.evaluate.apply(value, args);
            });
            return operator.apply(null, evaluatedOperands);
        },
        toString: function () {
            return this.arguments.join(" ") + " " + name;
        },
        diff: function (varName) {
            return diffOp(varName, this.arguments);
        },
        simplify: function () {
            if (this.arguments.every(function (arg) {
                return arg instanceof Const
            })) {
                return new Const(this.evaluate());
            } else {
                var res = Object.create(this);
                res.arguments = this.arguments.map(arg => arg.simplify());
                return res.toString() !== this.toString() ? res.simplify() : res;
            }
        }
    };
    return AbstractOperation;
}

var factory = operationFactory();

var Add = operationFactory(2, "+", (a, b) => a + b
    , function (varName, expr) {
        return new Add(
            expr[0].diff(varName),
            expr[1].diff(varName)
        );
    });

Add.prototype.simplify = function () {
    var res = factory.prototype.simplify.call(this);
    for (var key in res.arguments) {
        if (res.arguments[key].toString() === "0") {
            return res.arguments[1 - parseInt(key)];
        }
    }
    return res;
};

var Multiply = operationFactory(2, "*", (a, b) => a * b
    , function (varName, expr) {
        return new Add(
            new Multiply(
                expr[0].diff(varName),
                expr[1]
            ),
            new Multiply(
                expr[0],
                expr[1].diff(varName)
            )
        );
    });

Multiply.prototype.simplify = function () {
    var res = factory.prototype.simplify.call(this);
    for (var key in res.arguments) {
        if (res.arguments[key].toString() === "0") {
            return res.arguments[key];
        }
        if (res.arguments[key].toString() === "1") {
            return res.arguments[1 - parseInt(key)]; // diff value
        }
    }
    return res;
};

var Subtract = operationFactory(2, "-", (a, b) => a - b
    , function (varName, exprs) {
        return new Subtract(
            exprs[0].diff(varName),
            exprs[1].diff(varName)
        );
    });

Subtract.prototype.simplify = function () {
    var res = factory.prototype.simplify.call(this);
    for (var key in res.arguments) {
        if (res.arguments[key].toString() === "0") {
            return (key === '0' ? new Negate(res.arguments[1]) : res.arguments[0]);
        }
    }
    return res;
};

var Divide = operationFactory(2, "/", (a, b) => a / b
    , function (varName, exprs) {
        return new Divide( // (u / g)' = (u' * g - u * g') / g^2
            new Subtract(
                new Multiply(
                    exprs[0].diff(varName),
                    exprs[1]
                ),
                new Multiply(
                    exprs[0],
                    exprs[1].diff(varName)
                )
            ),
            new Multiply(
                exprs[1],
                exprs[1]
            )
        );
    });

Divide.prototype.simplify = function () {
    var res = factory.prototype.simplify.call(this);
    if (res instanceof Const) {
        return res;
    }
    if (res.arguments[0].toString() === "0" || res.arguments[1].toString() === "1") {
        return res.arguments[0];
    }
    return res;
};

var Negate = operationFactory(1, "negate", x => -x
    , function (varName, exprs) {
        return new Negate(exprs[0].diff(varName));
    });

Negate.prototype.simplify = function () {
    var res = factory.prototype.simplify.call(this);
    if (res.toString() === "0 negate") {
        return ZERO;
    }
    return res;
};

var Square = operationFactory(1, "square", x => x * x
    , function (varName, exprs) {
        return new Multiply( // (f(x)^2)' = 2f(x) * f(x)'
            TWO,
            new Multiply(
                exprs[0],
                exprs[0].diff(varName)
            )
        )
    });

let Sqrt = operationFactory(1, "sqrt", x => Math.sqrt(Math.abs(x))
    , function (varName, exprs) {
        return new Divide(
            new Multiply(
                exprs[0],
                exprs[0].diff(varName)
            ),
            new Multiply(
                TWO,
                new Sqrt(
                    new Multiply(
                        new Square(
                            exprs[0]
                        ),
                        exprs[0]
                    )
                )
            )
        )
    });

var ZERO = new Const(0);
var ONE = new Const(1);
var TWO = new Const(2);

var VARIABLES = {
    "x": 0,
    "y": 1,
    "z": 2
};

var OPERATIONS = {
    "+": Add,
    "-": Subtract,
    "*": Multiply,
    "/": Divide,
    "negate": Negate,
    "sqrt": Sqrt,
    "square": Square,
};

function isConst(token) {
    return (/^-*\d+$/).test(token); // * as symbol
}

function parse(expression) {
    var stack = [];
    const tokens = expression.split(/\s+/).filter(token => token.length());
    for (let i = 0; i < tokens.length; i++) {
        const token = tokens[i];
        if (token in VARIABLES) {
            stack.push(new Variable(token));
        } else if (token in OPERATIONS) {
            var curOperation = new OPERATIONS[token]();
            curOperation.arguments = stack.splice(curOperation.arguments.length - curOperation.length,
                curOperation.length);
            stack.push(curOperation);
        } else {
            stack.push(new Const(parseInt(token)));
        }
    }
    return stack.pop();
}
