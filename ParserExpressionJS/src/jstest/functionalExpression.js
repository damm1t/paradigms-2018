/**
 * Created by damm1t.
 * Date: 15.04.2018
 * Time: 11:36
 */
var variables = ["x", "y", "z"];
var variablesList = {};

variables.forEach(function (value) {
    variablesList[value] = [variable(value), 0];
    this[value] = variable(value);
});

function variable(name) {
    return function () {
        return arguments[variables.indexOf(name)];
    }
}

function cnst(value) {
    return function () {
        return value;
    }
}

//CONST
var pi = cnst(Math.PI);
var e = cnst(Math.E);

function anyCntOper(operator) {
    return function () {
        var expressions = arguments;
        return function () {
            var ans = [], args = arguments;
            Object.keys(expressions).forEach(function (i) {
                ans.push(expressions[i].apply(undefined, args));
            });
            return operator.apply(undefined, ans);
        }
    }
}

var add = anyCntOper(function (l, r) {
    return l + r;
});
var subtract = anyCntOper(function (l, r) {
    return l - r;
});
var multiply = anyCntOper(function (l, r) {
    return l * r;
});
var divide = anyCntOper(function (l, r) {
    return l / r;
});
var negate = anyCntOper(function (x) {
    return -x;
});
var min3 = anyCntOper(function (x, y, z) {
    return Math.min(x, y, z)
});
var max5 = anyCntOper(function (x, y, z, a, b) {
    return Math.max(x, y, z, a, b)
});

function getOperationByList(list) {
    return function (expr) {
        for (var operation in list) {
            if (expr === operation) {
                return [list[operation][0], list[operation][1]];
            }
        }
    }
}

var getOperation = getOperationByList({
    "+": [add, 2],
    "-": [subtract, 2],
    "*": [multiply, 2],
    "/": [divide, 2],
    "negate": [negate, 1],
    "min3": [min3, 3],
    "max5": [max5, 5]
});
var getLiteral = getOperationByList({"x": [x, 0], "y": [y, 0], "z": [z, 0]});
var getConstFromList = getOperationByList({"pi": [pi, 0], "e": [e, 0]});
var getVariable = getOperationByList(variablesList);

function getConst(expr) {
    var res = getConstFromList(expr);
    if (res !== undefined) {
        return res;
    }
    if (!/^-*\d+$/.test(expr)) {
        return undefined;
    }
    return [cnst(parseInt(expr)), 0];
}

function parse(expression) {
    var tokens = expression.split(/\s+/).filter(function (s) {
        return s !== "";
    });
    var stack = [];
    for (var i = 0; i < tokens.length; ++i) {
        var token = tokens[i];
        var variants = [getConst(token), getOperation(token),
            getVariable(token), getLiteral(token)].filter(function (value) {
            return value !== undefined;
        });
        for (var j = 0; j < variants.length; ++j) {
            var curVariant = variants[j];
            var expr = curVariant[0];
            var args = [];
            for (var kArgs = 0; kArgs < curVariant[1]; ++kArgs) {
                args[kArgs] = stack.pop();
            }
            expr = curVariant[1] ? expr.apply(undefined, args.reverse()) : expr;
            stack.push(expr);
            break;
        }
    }
    return stack[0];
}
