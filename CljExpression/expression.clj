(defn operator [func & unary]
  (fn [& operands]
    {:pre [(or (empty? unary) (== 1 (count operands)))]}
    (fn [vars] (apply func (map #(% vars) operands)))))

(def constant constantly)

(defn variable [name]
  #(% name))
;(fn [evaluate] (evaluate name))

(defn div
  ([arg] (/ arg))
  ([arg & args] (reduce (fn [x y] (/ x (double y))) arg args)))

(def add (operator +))
(def subtract (operator -))
(def multiply (operator *))
(def negate (operator - 'unary))
(def divide (operator div))

(def sinh (operator (fn [val] (Math/sinh val)) 'unary))
(def cosh (operator (fn [val] (Math/cosh val)) 'unary))

(def operations {'+ add, '- subtract, '* multiply, '/ divide, 'negate negate, 'sinh sinh, 'cosh cosh})

(defn parser [expression]
  (cond
    (seq? expression) (apply (operations (first expression)) (map parser (rest expression)))
    (number? expression) (constant expression)
    :else (variable (str expression))))

(def parseFunction
  (comp parser read-string))
