(defn equals-len?
  ([] true)
  ([_] true)
  ([vector1 & vectors]
   (let [cnt (count vector1)] (every? (fn [x] (== (count x) cnt)) vectors))))


(defn vec? [& vector] (every? vector? vector))

(defn matrix? [matrix] (and (vector? matrix) (apply vec? matrix) (apply equals-len? matrix)))

(defn operator [func]
  (fn [& args]
    {:pre [(apply equals-len? args)]}
    (apply mapv func args)))

(defn operate-*s [func]
  (fn
    ([vector]
     {:pre [(vec? vector)]}
     vector)
    ([vector s1 & s]
     {:pre [(and (vec? vector) (number? s1) (apply map number? s))]}
     (let [mul (* s1 (apply * s))]
       (mapv (fn [x] (func x mul)) vector)))))

;(def v+ [& args] (apply add args))
(def v+ (operator +))
(def v- (operator -))
(def v* (operator *))
(defn scalar [& args] (apply + (apply v* args)))
(defn transpose [args]
  {:pre [(matrix? args)]}
  (apply mapv vector args))
(defn vect
  ([v]
   {:pre [(and (vec? v) (== 3 (count v)))]}
   v)
  ([v1 v2]
   {:pre [(and (vec? v1) (vec? v2) (equals-len? v1 v2) (== 3 (count v1)))]}
   [(- (* (v1 1) (v2 2)) (* (v1 2) (v2 1)))
    (- (* (v1 2) (v2 0)) (* (v1 0) (v2 2)))
    (- (* (v1 0) (v2 1)) (* (v1 1) (v2 0)))])
  ([v1 v2 & v]
   (reduce vect (vect v1 v2) v)))


(def v*s (operate-*s *))

;(defn m+ [& args] (apply mapv v+ args))
(def m+ (operator v+))
(def m- (operator v-))
(def m* (operator v*))
(def m*s (operate-*s v*s))
(defn m*v [matrix1 vector1]
  {:pre [(and (matrix? matrix1) (vec? vector1))]}
  (mapv (fn [row] (scalar row vector1)) matrix1))
(defn m*m
  ([] 1.0)
  ([m]
   {:pre [(matrix? m)]}
   m)
  ([matrix1 matrix2]
   {:pre [(and (matrix? matrix1) (matrix? matrix2))]}
   (mapv (fn [row1]
           (mapv (fn [row2]
                   (scalar row1 row2))
                 (transpose matrix2)))
         matrix1))
  ([matrix1 matrix2 & matrix] (reduce m*m (m*m matrix1 matrix2) matrix)))

;easy mod
;############################################################################
(defn operate-se [action]
  (fn merge
    ([vector] vector)
    ([vector1 vector2]
      ;{:pre [(equals-len? vector1 vector2)] :post [(equals-len? % vector1)]}
     (if (number? vector1)
       (action vector1 vector2)
       (mapv merge vector1 vector2)))
    ([vector1 vector2 & vectors] (reduce merge (merge vector1 vector2) vectors))))

(def s+ (operate-se +))
(def s- (operate-se -))
(def s* (operate-se *))
;##################################################################################
;hard mod
(defn shape [a]
  (if (number? a)
    ()
    (cons (count a) (shape (first a)))))

(defn operate-f [func]
  (fn
    ([v]
     (if (number? v)
       (func v)
       (mapv (operate-f func) v)))
    ([v1 v2]
     (if (number? v1)
       (func v1 v2)
       (mapv (operate-f func) v1 v2)))))

(defn broadcast [t s]
  {:pre (= (nthrest s (- (count s) (count (shape t)))) (shape t))}
  (if (equals-len? (shape t) s)
    t
    (vec (repeat (nth s 0) (broadcast t (rest s))))))

(defn operate-t [func]
  (fn
    ([t1] ((operate-f func) t1))
    ([t1 t2]
     (let [max-shape (max-key count (shape t1) (shape t2)) t1 (broadcast t1 max-shape) t2 (broadcast t2 max-shape)]
       ((operate-f func) t1 t2)))
    ([t1 t2 & t]
     (reduce (operate-t func) ((operate-t func) t1 t2) t))))

(def b+ (operate-t +))
(def b* (operate-t *))
(def b- (operate-t -))
