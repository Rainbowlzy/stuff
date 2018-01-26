(ns spider-one.core
  (:require [itsy.core :refer :all])
  (:use [clj-xpath.core :only [$x $x:tag $x:text $x:attrs $x:attrs* $x:node]]))


(defn my-handler [{:keys [url body]}]
  (println url "has a count of" (count body)))



(def c (crawl {;; initial URL to start crawling at (required)
               :url "http://mac.system-onlline.com/eng/5/index.html?osversion=OS%20X%2010.12&clickid=&voluumdata=BASE64dmlkLi4wMDAwMDAwNy05NzQ0LTQ0ZmQtODAwMC0wMDAwMDAwMDAwMDBfX3ZwaWQuLjMzZjExODAwLTFkMzUtMTFlNy04ODVjLTFjN2I4ZDhkY2M4Zl9fY2FpZC4uN2M1MTdlYzEtMTlmZS00NTE4LTk5NWYtMWY4ZjkwOWM2MjJhX19ydC4uSF9fbGlkLi5jMzA0OTU5ZS03N2UyLTRlNDktYWI3MC00MjhiNmY5YmE1MDlfX29pZDEuLjgxNzMzMTMzLWMxNTItNDk5Yi05OWQ0LWJmMDkzNjYxZjhiZV9fdmFyMS4uMTc2NTMzOV9fdmFyMi4ud2hpY2hhdlwuXGNvbV9fdmFyMy4uNTQ3NjMzX192YXI0Li4yMTg4MjY3X192YXI1Li45OV9fcmQuLnN5bmRpY2F0aW9uXC5cZXhvY2xpY2tcLlxjb21fX2FpZC4uX19hYi4uX19zaWQuLl9fY3JpLi5fX3B1Yi4uX19kaWQuLl9fZGl0Li5fX3BpZC4uX19pdC4uX192dC4uMTQ5MTc1MDQ3NzM4Mg&campaign_id=1765339&src_hostname=whichav.com&site_id=547633&zone_id=2188267&category_id=99&id="
               
               ;; handler to use for each page crawled (required)
               :handler my-handler
               ;; number of threads to use for crawling, (optional,
               ;; defaults to 5)
               :workers 10
               ;; number of urls to spider before crawling stops, note
               ;; that workers must still be stopped after crawling
               ;; stops. May be set to -1 to specify no limit.
               ;; (optional, defaults to 100)
               :url-limit 99999
               ;; function to use to extract urls from a page, a
               ;; function that takes one argument, the body of a page.
               ;; (optional, defaults to itsy's extract-all)
               :url-extractor extract-all
               ;; http options for clj-http, (optional, defaults to
               ;; {:socket-timeout 10000 :conn-timeout 10000 :insecure? true})
               :http-opts {}
               ;; specifies whether to limit crawling to a single
               ;; domain. If false, does not limit domain, if true,
               ;; limits to the same domain as the original :url, if set
               ;; to a string, limits crawling to the hostname of the
               ;; given url
               :host-limit false
               ;; polite crawlers obey robots.txt directives
               ;; by default this crawler is polite
               :polite? true}))

;; ... crawling ensues ...

;; (thread-status c)
;; ;; returns a map of thread-id to Thread.State:

;; (add-worker c)
;; ;; adds an additional thread worker to the pool

;; (remove-worker c)
;; ;; removes a worker from the pool

;; (stop-workers c)
(clojure.pprint/pprint (:state c))
(count @(:seen-urls (:state c)))
(spit "tmp-new.org" (str @(:seen-urls (:state c))))

(vector (map first (filter #(= 0 (.indexOf (first %) "http://xdn.wokao.co/video/embed/")) @(-> c :state :seen-urls))))



























