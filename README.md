
## ThreeTen backport project
JSR-310 provides a new date and time library for JDK 1.8.
This project is the backport to JDK 1.7.

The backport is NOT an implementation of JSR-310, as that would require
jumping through lots of unecessary hoops.
Instead, this is a simple backport intended to allow users to quickly
use the JSR-310 API on JDK 1.7.
The backport should be referred to using the "ThreeTen" name.

Active development on JSR-310 is at [OpenJDK](http://openjdk.java.net/projects/threeten/):

This GitHub repository is a fork of that originally used to create JSR-310.
That repository used the same BSD 3-clause license as this repository.

Issues should be reported here at GitHub.
Pull requests and issues will only be considered so far as matching the behavior
of the real JSR-310. Additional requested features will be rejected.

#### Building
This project builds using maven.

#### Time-zone data
The time-zone database is stored as a pre-compiled dat file that is included in the built jar.
The version of the time-zone data used is stored within the dat file (near the start).
Updating the time-zone database involves using the `TzdbZoneRulesCompiler` class
and re-compiling the jar file.
Pull requests with later versions of the dat file will be accepted.

#### FAQs

1. What version of JDK 1.8 does this project map to?
This project currently maps to the M7 milestone.

2. Will the backport be kept up to date?
There will be a release matching the final JDK 1.8 version.
There may or may not be further updates between now and then.

3. Is this project derived from OpenJDK?
No. This project is derived from the Reference Implementation previously hosted on GitHub.
That project had a BSD license, which has been preserved here.
Thus, this project is a fork of the original code before entry to OpenJDK.
