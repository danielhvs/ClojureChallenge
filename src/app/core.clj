(ns app.core)

(def zero-or-less? (complement pos?))

(defn scramble?
  "Returns true if a portion of scrambled-word characters can be rearranged to match word, otherwise returns false.
   Only lower case letters will be used (a-z). No punctuation or digits will be included."
  [scrambled-word word]
  (let [scrambled-freqs (frequencies scrambled-word)
        word-freqs (frequencies word)
        word-chars (keys word-freqs)]
    (every?
      zero-or-less?
      (->> word-chars
           (select-keys scrambled-freqs)
           (merge-with - word-freqs)
           vals))))
