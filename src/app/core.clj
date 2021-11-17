(ns app.core)

(def less-than-or-equal-zero? (complement pos?))

(defn scramble?
  "returns true if a portion of scrambled-word characters can be rearranged to match word, otherwise returns false"
  [scrambled-word word]
  (let [scrambled-freqs (frequencies scrambled-word)
        word-freqs (frequencies word)]
    (every?
      less-than-or-equal-zero?
      (vals
        (merge-with - word-freqs
                    (select-keys scrambled-freqs (keys word-freqs)))))))
