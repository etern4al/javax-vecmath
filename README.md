[![Build Result](https://api.travis-ci.org/RalleYTN/javax-vecmath.svg?branch=master)](https://travis-ci.org/RalleYTN/javax-vecmath)
[![Coverage Status](https://coveralls.io/repos/github/RalleYTN/javax-vecmath/badge.svg?branch=master)](https://coveralls.io/github/RalleYTN/javax-vecmath?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/60d897fa72f349a6bee5984be6a29caa)](https://www.codacy.com/app/ralph.niemitz/javax-vecmath?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=RalleYTN/javax-vecmath&amp;utm_campaign=Badge_Grade)

# Description

A fork of [javax.vecmath](https://github.com/kenjihiranabe/javax.vecmath) that was cleaned up and made compatible with Java 9 modules.

## List of changes made to the original library

- Created a `module-info.java` file
- Made the methods `equals`, `toString`, `hashCode` and `epsilonEquals` final
- Made all classes final
- Cleaned the code a bit (someone clearly didn't know what a tabulator was)
- The unit tests were ported to JUnit

## License

```
Copyright (C) 1997,1998,1999
Kenji Hiranabe, Eiwa System Management, Inc.

This program is free software.
Implemented by Kenji Hiranabe(hiranabe@esm.co.jp),
conforming to the Java(TM) 3D API specification by Sun Microsystems.

Permission to use, copy, modify, distribute and sell this software
and its documentation for any purpose is hereby granted without fee,
provided that the above copyright notice appear in all copies and
that both that copyright notice and this permission notice appear
in supporting documentation. Kenji Hiranabe and Eiwa System Management,Inc.
makes no representations about the suitability of this software for any
purpose. It is provided "AS IS" with NO WARRANTY.
```

## Links

- [Original Repository](https://github.com/kenjihiranabe/javax.vecmath)
- [Online Documentation](https://ralleytn.github.io/javax-vecmath/)
- [Download](https://github.com/RalleYTN/javax-vecmath/releases)