(ns playground.pdfprocess)

(:import [org.apache.pdfbox.pdmodel PDDocument]
         [org.apache.pdfbox.util PDFTextStripper])

(defn text-of-pdf
  [url]
  (with-open [pd (PDDocument/load (URL. url))]
    (let [stripper (PDFTextStripper.)]
      (.getText stripper pd))))
