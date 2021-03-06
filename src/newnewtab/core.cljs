(ns newnewtab.core
  (:require [newnewtab.util :as u]
            [newnewtab.chrome-apis :as chrome]
            [clojure.string :as s]))

(defn to-a
  "Makes a link tag as a string"
  [location text]
  (str "<a href=\"" location "\">" text "</a>"))

(defn to-li
  [s]
  (str "<li>" s "</li>"))

(defn list-of-links
  [objects]
  (reduce (fn [s o]
            (str s (to-li (to-a (.-url o) (.-title o)))))
          ""
          objects))

(doseq [event ["keyup" "click"]]
  ;; search through your bookmarks
  (u/on (u/find-selector ".bookmarks-search")
        event
        #(let [search-for (.-value (u/find-selector ".bookmarks-search"))
               results-el (u/find-selector ".bookmarks-results")]
           (if (s/blank? search-for)
             (u/replace-contents results-el "")
             (chrome/search-bookmarks
              search-for
              (fn [results]
                (u/replace-contents results-el
                                    (list-of-links results))))))))

(chrome/top-sites #(u/replace-contents (u/find-id "top-sites")
                                       (list-of-links %)))

;; don't reload the newtab page when forms that go nowhere are submitted
(doseq [some-search-id ["bookmarks"]]
  (u/on (u/find-id some-search-id)
        "submit"
        #(.preventDefault %)))
