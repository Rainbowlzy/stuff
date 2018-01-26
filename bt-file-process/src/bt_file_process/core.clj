(ns bt-file-process.core)

(let []
  (use '[bencode.core])
  (use '[bencode.metainfo.reader])
  (require '[det-enc.core :as det])
  )

;; At this point, you should be able to use bencode and bdecode functions for encoding and decoding, respectively:
(bencode {:cow "moo" :spam ["info" 32]})
;; -> "d3:cow3:moo4:spaml4:infoi32eee"
(bdecode "d3:cow3:moo4:spaml4:infoi32eee")
;; -> {:cow "moo", :spam ["info" 32]}
;; (torrent-name (parse-metainfo-file "/Volumes/1T/rtys/torrent/1085420150105060927.torrent"))
(defn find-torrents
  "在指定路径下寻找bt种子文件"
  [path]
  (filter (fn [f] (.endsWith (.getAbsolutePath f) ".torrent")) (file-seq (clojure.java.io/file path))))
(defn get-torrent-name [f] (str (.getParent f) \/ (torrent-name (parse-metainfo-file (.getAbsolutePath f))) ".torrent"))
(defn rename-torrent [f]
  (try
    (let [original-file f
          new-file (clojure.java.io/file (get-torrent-name f))]
      (when (not (= (.getAbsolutePath original-file) (.getAbsolutePath new-file)))
        (clojure.java.io/copy f new-file)
        (clojure.java.io/delete-file original-file)
        (println (.getAbsolutePath new-file))
        new-file)
      )
    (catch Exception ex (str (.getAbsolutePath f) \: (.getMessage ex)))))
;; (map rename-torrent (find-torrents "/Volumes/XiaoMi-usb0/"))
(def encodings [
                "UTF-8"
                "UTF-16"
                "UTF-32"
                "GBK"
                "GB2312"
                "GB18030"
                "ISO-8859-1"
                "ISO-2022-JP"])

(defn try-encoding
  [s encoding]
  (str \[ encoding \] " " (new String (byte-array (.getBytes s)) encoding)))

(defn try-encoding-fn
  [s]
  (fn [encoding] (try-encoding s encoding)))

(def efn (try-encoding-fn "[����Ƥ����] �xĸɢ�A ǰ��.torrent"))




