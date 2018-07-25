# libgdx-ios12-sample
Reproduces an issue with iOS 12 beta and libGDX

libGDX sample app with desktop and iOS platforms that repeatedly opens and closes a native dialog, along with some GC use every 100ms.

Sample test runs:
run 1: App not responding after 42 sec
run 2: still alive after 300 sec...
run 3: ANR after 250 sec
run 4: ANR after 29 sec
run 5: ANR after 259 sec
run 6: ANR after 58 sec
