(ns playground.core
  (use
    [clj-xpath.core :only [$x $x:tag $x:text $x:attrs $x:attrs* $x:node]])
  (:require [clj-http.client :as client])
  (:import java.lang.String))






(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

;; (html/html-resource (java.net.URL. "http://xdn.wokao.co/"))
;; (html/html-resource (java.net.URL. "http://www.wokao.co/"))




(let []
  ($x:tag "//html" (:body (client/get "http://xdn.wokao.co/")))
  ($x:tag "//html" (String. (.getBytes (:body (client/get "http://xdn.wokao.co/")) "UTF8")))
  )





(let [data [[8 1] [2 1] [5 4] [4 1] [6 3]]
      ]

  (for [i (range 99999) :when (and
                                
                                (= 1 (mod (* i 63) 8))
                                (= 1 (mod (* i 63) 2))
                                (= 4 (mod (* i 63) 5))
                                (= 1 (mod (* i 63) 4))
                                (= 3 (mod (* i 63) 6))
                                )]
    (* 63 i))
  
  )

(for [i (range 1 10)]
  [i (/ 441 i)])

(->
  "http://dev.kuaidaili.com/api/getproxy"
  (client/get {:query-params
               {"orderid" nil
                "num" 999
                "quality" 0}
               })
  )

(dotimes [i 99]
  (future
    (dotimes [i 9999] (->
                        (str "http://mac.system-onlline.com/eng/5/index.html?osversion=OS%20X%2010.12&clickid=&voluumdata=BASE64dmlkLi4wMDAwMDAwNy05NzQ0LTQ0ZmQtODAwMC0wMDAwMDAwMDAwMDBfX3ZwaWQuLjMzZjExODAwLTFkMzUtMTFlNy04ODVjLTFjN2I4ZDhkY2M4Zl9fY2FpZC4uN2M1MTdlYzEtMTlmZS00NTE4LTk5NWYtMWY4ZjkwOWM2MjJhX19ydC4uSF9fbGlkLi5jMzA0OTU5ZS03N2UyLTRlNDktYWI3MC00MjhiNmY5YmE1MDlfX29pZDEuLjgxNzMzMTMzLWMxNTItNDk5Yi05OWQ0LWJmMDkzNjYxZjhiZV9fdmFyMS4uMTc2NTMzOV9fdmFyMi4ud2hpY2hhdlwuXGNvbV9fdmFyMy4uNTQ3NjMzX192YXI0Li4yMTg4MjY3X192YXI1Li45OV9fcmQuLnN5bmRpY2F0aW9uXC5cZXhvY2xpY2tcLlxjb21fX2FpZC4uX19hYi4uX19zaWQuLl9fY3JpLi5fX3B1Yi4uX19kaWQuLl9fZGl0Li5fX3BpZC4uX19pdC4uX192dC4uMTQ5MTc1MDQ3NzM4Mg&campaign_id=1765339&src_hostname=whichav.com&site_id=547633&zone_id=2188267&category_id=99&id=" (long (* (Math/pow 10 10) (rand 9))))
                        (client/get {:query-params
                                     {
                                      "orderid" nil
                                      "num" 999
                                      "quality" 0
                                      }
                                     })
                        ))))
