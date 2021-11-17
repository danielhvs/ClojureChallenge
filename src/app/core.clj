(ns app.core)

(defn scramble?
  "returns true if a portion of str1 characters can be rearranged to match str2, otherwise returns false"
  [str1 str2]
  (let [mapa1 (frequencies str1)
        mapa2 (frequencies str2)]
    (every?
      (complement pos?)
      (vals
        (merge-with - mapa2
                    (select-keys mapa1 (keys mapa2)))))))

(scramble? "rekqodlw" "world") ; ==> true
(scramble? "cedewaraaossoqqyt" "codewars") ;  ==> true
(scramble? "katas" "steak") ; ==> false
