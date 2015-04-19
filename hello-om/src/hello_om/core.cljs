(ns hello-om.core
  (:require [om.core :as om]
            [om.dom :as dom]))

(enable-console-print!)

(def app-state
  (atom {
         :text "Hello World!"
         :body "Clojure/West 2015"
         :server-url ""
         :server-data-cache ""
         }))

(def h1-styles {:color "#69c"
              :backgroundColor "#ccc"})
(om/root
 (fn [app owner]
   (reify om/IRender
     (render [_]
       (println (pr-str app owner))
       (js/console.log owner)
       (dom/h1
        nil
        (:body app)))))
 app-state
 {:target (js/document.getElementById "app")})

(defn simple-widget [data owner]
  (reify
    om/IRender
    (render [_]
      (dom/h1 nil "Hello world!"))))

(defn jw-widget [data owner]
  (reify
    om/IRender
    (render [_]
      (dom/h2 nil (:text app-state)))))

(defn my-widget [data owner]
  (reify
    om/IInitState
    (init-state [_]
      {:text "Hello world!"})
    om/IRenderState
    (render-state [_ state]
      (dom/h1 nil (:text state)))))
