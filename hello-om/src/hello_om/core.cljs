(ns hello-om.core
  (:require [om.core :as om]
            [om.dom :as dom]))

(enable-console-print!)

(def app-state (atom {:count 0}))
(def history (atom [@app-state] ))

(defn update-count [app]
  (om/transact! app [:count] inc))

(add-watch app-state
           :history
           (fn [key rf old new]
             (println old new history)
             (swap! history old (conj old new))))

(defn undo! [e]
  (js/console.log "undo!"))

(defn counter [app owner]
  (reify
    om/IRender
    (render [_]
      (dom/div nil
               (dom/p nil (str "Count: " (:count app)))
               (dom/button
                #js {:onClick (fn [e] (update-count app))}
                "Click me!")
               (dom/button
                #js {:onClick undo!}
                "Undo")
               ))))

(om.core/root
 counter
 app-state
 {:target (js/document.getElementById "app")})
