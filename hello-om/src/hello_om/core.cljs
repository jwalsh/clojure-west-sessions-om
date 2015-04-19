(ns hello-om.core
  (:require [om.core :as om]
            [om.dom :as dom]))

(enable-console-print!)

(def app-state (atom {:count 0}))
(def history (atom [@app-state] ))

(defn update-count [app]
  (om/transact! app [:count] inc))

(defn counter [app owner]
  (reify
    om/IRender
    (render [_]
      (dom/div nil
               (dom/p nil (str "Count: " (:count app)))
               (dom/button
                #js {:onClick (fn [e] (update-count app))}
                "Click me!")))))

(om.core/root
 counter
 app-state
 {:target (js/document.getElementById "app")})
