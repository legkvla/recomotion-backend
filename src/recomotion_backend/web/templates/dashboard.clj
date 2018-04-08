(ns recomotion-backend.web.templates.dashboard
  (:require
    [hiccup.page :refer [include-js include-css html5]]
    [recomotion-backend.storages.events :as events]
    )
  (:use clojure.tools.trace)
  )

(defn glyphicon [id]
  [:span {:class (keyword (str "glyphicon glyphicon-" (name id)))}]
  )

(def nbsp "&nbsp;")

(def body-style {:style "margin-left: 250px; padding: 25px;"})
(def background-style
  {:style (str
    "background-size: cover;"
    "background-image: url(\"/img/dion-tavenier-107010.jpg\");"
    )}
  )

(defn head []
  [:head
   [:title "Recomotion"]
   [:meta {:charset "UTF-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   (include-css "https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css")
   (include-css "https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css")
   (include-js "https://code.jquery.com/jquery-3.2.1.min.js")
   (include-js "https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js")
   (include-js "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js")
   ]
  )

(defn page-layout [style & args]
  (html5
    (head)
    (into (if (= style :background) [:body background-style] [:body]) args)
    )
  )

(defn menu []
  [:div.navbar.navbar-default
    [:div.container-fluid
      [:div.navbar-header
        [:a.navbar-brand "Recomotion"]
        ]
      [:div.menu-list
        [:ul.nav.navbar-nav
          [:li.active
            [:a
              {:href "/dashboard"}
              (glyphicon :dashboard)
              " Dashboard"
              ]
            ]
          ]
        ]
      ]
    ]
  )

(defn dashboard []
  (page-layout :simple
    (menu)
    [:div.col-md-offset-3
      [:iframe {:width 720 :height 405 :src "//rutube.ru/play/embed/7848616"}]
      ]
    [:div.col-md-12
      [:canvas#line-chart {:width 800 :height 450}]
      ]
    )
  (include-js "/public/js/chart.js")  
  )
