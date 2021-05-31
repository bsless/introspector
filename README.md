# introspector

A bunch of tools to inspect the current JVM process.

## Usage

Require the inspector namespace

```clojure
(require '[com.github.bsless.introspector :as i])
```

Use the functions:
- `input-arguments`: See the input arguments to the JVM process.
- `class-path`: See the class path to the JVM process.
- `java-home`: Get the `java.home` property of the current JVM process.
- `properties`: Get all of the current JVM process properties.
- `pid`: Get the PID of the current JVM process.
- `visual-vm!`: attach a visualvm to the current JVM with correct `java.home`.

## License

Copyright © 2021 Ben Sless

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
