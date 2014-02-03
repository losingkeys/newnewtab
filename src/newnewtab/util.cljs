(ns newnewtab.util)

;;FIXME: make a macro for applying argument native fake functions
;; (defn log
;;   [& args]
;;   (apply args js/console.log))

(defn on-document-ready
  "Calls a callback function when the document's readyState is \"complete\"

More info: and https://developer.mozilla.org/en-US/docs/Web/API/document.readyState"
  [cb & args]
  (on js/document
      "readystatechange"
      #(when (= (.-readyState js/document) "complete")
         (apply cb args))))

(defn replace-contents
  [target new-contents]
  (set! (.-innerHTML target) new-contents))

(defn on
  "Adds a event listener
Small wrapper around: https://developer.mozilla.org/en-US/docs/Web/API/EventTarget.addEventListener

Arguments:
target - the target element/object to listen for the event on
event-name - the name of the event to listen for
cb - the callback to call when the event is triggered"
  [target event-name cb]
  (.addEventListener target event-name cb))

(defn find-id
  "Finds an element on the page by its id
Small wrapper around: https://developer.mozilla.org/en-US/docs/Web/API/document.getElementById

id - the id of the element to find

returns nil if no element is found"
  [id]
  (.getElementById js/document id))

(defn find-selector
  "Finds an element by a specified query selector
Small wrapper around: https://developer.mozilla.org/en-US/docs/Web/API/document.querySelector and https://developer.mozilla.org/en-US/docs/Web/API/Element.querySelector

selector - the query selector
(optional) search-in - the container to search in (searches js/document if this isn't specified)

returns nil if no elements are found. Returns at most one element"
  ([selector] (find-selector selector js/document))
  ([selector search-in]
     (.querySelector search-in selector)))

(defn find-selector-all
  "Finds element[s] by a specified query selector
Small wrapper around: https://developer.mozilla.org/en-US/docs/Web/API/document.querySelectorAll and https://developer.mozilla.org/en-US/docs/Web/API/Element.querySelectorAll

selector - the query selector
(optional) search-in - the container to search in (searches js/document if this isn't specified)

returns a vector containing 0 or more elements matching the specified selector
FIXME: this returns a NodeList instead of a vector of Elements"
  ([selector] (find-selector-all selector js/document))
  ([selector search-in]
     (.querySelectorAll search-in selector)))
