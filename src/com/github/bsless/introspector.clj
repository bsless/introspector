(ns com.github.bsless.introspector
  (:require
   [clojure.string :as str])
  (:import
   (java.lang ProcessBuilder)
   (java.lang.management ManagementFactory RuntimeMXBean)))

(defonce +runtime-bean+ (delay (ManagementFactory/getRuntimeMXBean)))

(defn ^RuntimeMXBean runtime-bean [] @+runtime-bean+)

(comment
  (runtime-bean))

(defn input-arguments
  "Get the JVM's input arguments.
  Useful for making sure there are no surprises."
  []
  (into [] (.getInputArguments (runtime-bean))))

(comment
  (input-arguments))

(defonce +class-path+ (delay (System/getProperty "java.class.path")))

(defn class-path
  "Get the JVM's class path."
  []
  (str/split @+class-path+ #":"))

(defonce +java-home+ (delay (System/getProperty "java.home")))

(defn java-home
  "Get current JVM's java.home.
  By default trims the jre directory from the path."
  ([]
   (java-home true))
  ([trim-jre?]
   (->
    +java-home+
    deref
    (cond->
        trim-jre? (str/replace #"/jre" "")))))

(comment
  (java-home)
  (java-home false))

(defonce +properties+ (delay (into {} (System/getProperties))))

(defn properties
  "Get all of the current JVM's properties."
  []
  @+properties+)

(comment
  (properties))

(defn pid
  "Get current JVM's PID as a string."
  []
  (-> (runtime-bean)
      .getName
      (str/split #"@")
      first))

(defonce +vm-proc+ (atom nil))
(comment @+vm-proc+)

(defn- default-visual-vm-opts
  []
  {:jdkhome (java-home)
   :openpid (pid)})

(defn- build-visual-vm-args
  ([]
   (build-visual-vm-args {}))
  ([opts]
   (into
    ["visualvm"]
    (comp
     (map (fn [[k v]] [(str "--" (name k)) (str v)]))
     cat)
    (merge (default-visual-vm-opts) opts))))

(comment
  (build-visual-vm-args)
  (build-visual-vm-args {:openpid 1}))

(defn visual-vm!
  "Attach a visualvm process to the current JVM.
  Optionally takes a map of arguments which correspond to the visualvm flags:
  --jdkhome => `:jdkhome`"
  ([]
   (visual-vm! {}))
  ([opts]
   (locking +vm-proc+
     (let [args (build-visual-vm-args opts)
           pb (doto (ProcessBuilder. args)
                (.redirectErrorStream true)
                (.inheritIO))
           p (.start pb)]
       (reset! +vm-proc+ p)))))

(comment
  (visual-vm!))
