.PHONY: build-ignore-test
build-ignore-test:
	@sbt "set assembly/test := {}; set packageBin/test := {}" clean scalafmtCheckAll scalafmtSbtCheck  server/universal:packageBin

.PHONY: build-ignore-test-server-only
build-ignore-test-server-only:
	@sbt "set assembly/test := {}; set packageBin/test := {}" clean scalafmtCheckAll scalafmtSbtCheck server/universal:packageBin


.PHONY: build-ignore-test-job-only
build-ignore-test-job-only:
	@sbt "set assembly/test := {}; set packageBin/test := {}" clean scalafmtCheckAll scalafmtSbtCheck

.PHONY: build
build:
	@sbt clean scalafmtCheckAll scalafmtSbtCheck server/universal:packageBin


.PHONY: fmt
fmt:
	@sbt scalafmtSbt scalafmtAll

.PHONY: format-check
format-check:
	@sbt scalafmtCheckAll scalafmtSbtCheck


.PHONY: test
test:
	@sbt clean scalafmtCheckAll scalafmtSbtCheck test
