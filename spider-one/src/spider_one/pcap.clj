(ns spider-one.pcap
  (:require [clj-http.client :as client]
            [clj-xpath.core :refer :all]
            [clojure.string :as str]))


(dotimes [x 100] (let [qorglist [["红苹果" "青苹果" "苹果"]
                                 ["小红花" "大红花" "红花"]
                                 ["女孩" "男孩" "孩子"]
                                 ["香蕉" "橘子" "水果"]
                                 ["布娃娃" "遥控汽车" "玩具"]]
                       qlist (concat qorglist qorglist)
                       qcurr (nth qlist (int (* 10 (Math/random))))
                       a (int (* 100 (Math/random)))
                       b (int (* 100 (Math/random)))]
                   (prn (format "%s有%d个，%s有%d个，问共有%s多少个？ 答：%d个。" (first qcurr) a (second qcurr) b (last qcurr) (+ a b)))))
