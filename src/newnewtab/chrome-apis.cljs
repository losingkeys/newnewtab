(ns newnewtab.chrome-apis)

(defn search-bookmarks
  "Searches your bookmarks. Requires the 'bookmarks' permission in your manifest.json:

  ...
  \"permissions\": [\"bookmarks\"]
  ...

Small wrapper around: http://developer.chrome.com/extensions/bookmarks.html#method-search

for - a string or object (FIXME: properly support objects) detailing what to search the bookmarks for
cb - the callback function to call when finished searching"
  [for cb]
  (.search (.-bookmarks js/chrome)
           for
           cb
;           #(apply cb (js->clj %&))
           ))
  ;;FIXME: l2use -> and rewrite this?
