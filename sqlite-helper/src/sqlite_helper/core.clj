(ns sqlite-helper.core)
(require '[clojure.java.jdbc :refer :all])
;; (def driver "" "/Volumes/XiaoMi-usb0/") ;; 失败

;; (spit "/Users/apple/Downloads/TempDB/etm.db" (slurp "/Volumes/XiaoMi-usb0/ThunderDB/etm.db")) ;; 失败

(def driver "" "/Users/apple/Downloads/")

(def thunderdb2
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     (str driver "ThunderDB2/etm.db")
   })

(def thunderdb1
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     (str driver "ThunderDB1/etm.db")
   })

(def thunderdb
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     (str driver "ThunderDB/etm.db")
   })

;; [
;;  (query thunderdb ["select count(*) count from task_info"])
;;  (query thunderdb1 ["select state,name from task_info order by state"])
;;  (query thunderdb2 ["select state,count(*) count from task_info group by state"])
;;  ]

(query thunderdb2 ["select count(*) count from task_info"]) ;; 直接读取DB失败
