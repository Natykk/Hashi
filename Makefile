PACKAGE=com.hashi
MAIN:=Main

SRC=./src
DOC=./doc
CLASS=./class
ifeq ($(OS), Windows_NT)
FILES=$(shell dir /s *.java $(SRC))
else
FILES=$(shell find $(SRC) -type f -name *.java)
endif

.PHONY: build
build:
	@javac -d $(CLASS) $(FILES)

.PHONY: $(MAIN)
$(MAIN):
	@cd $(CLASS) && java $@

.PHONY: doc
doc:
	@javadoc -d $(DOC) -sourcepath $(SRC) -subpackages $(PACKAGE)