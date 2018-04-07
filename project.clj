(defproject recomotion-backend "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
    [org.clojure/clojure "1.8.0"]
    [org.clojure/core.async "0.3.465"]
    [ring-server "0.4.0"]
    [ring "1.5.0"]
    [ring/ring-json "0.4.0"]
    [cheshire "5.5.0"]
    [ring/ring-defaults "0.2.1"]
    [ring-logger "0.7.7"]
    [compojure "1.5.1"]
    [buddy "1.3.0"]
    [buddy/buddy-auth "1.4.1"]
    [com.novemberain/monger "3.1.0"]
    [environ "1.0.0"]
    [org.clojure/tools.trace "0.7.9"]
    ]

  :main ^:skip-aot recomotion-backend.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  )
