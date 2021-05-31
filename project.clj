(defproject com.github.bsless/introspector "0.0.0-SNAPSHOT"
  :description "Inspect the current JVM process"
  :url "https://github.com/bsless/introspector"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :deploy-repositories [["clojars" {:url "https://clojars.org/repo"
                                    :username :env/clojars_user
                                    :password :env/clojars_token
                                    :sign-releases false}]
                        ["releases" :clojars]
                        ["snapshots" :clojars]]
  :dependencies [[org.clojure/clojure "1.10.3"]]
  :repl-options {:init-ns com.github.bsless.introspector})
