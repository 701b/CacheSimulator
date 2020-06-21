#-----------------------------------------------------------
# Student Name  : Moon Taeui
# Student ID No : 2016312021
#-----------------------------------------------------------
CLASSES_DIR = bin

JAVAC = javac
JAVAC_FLAGS = -d $(CLASSES_DIR)
JAVA = java
JAVA_FLAGS = -cp $(CLASSES_DIR)

SOURCES = \
    *.java\
	cache/*.java

CLASSES = \
	$(CLASSES_DIR)/*.class \
	$(CLASSES_DIR)/cache/*.class


default:
	mkdir $(CLASSES_DIR)
	$(JAVAC) $(JAVAC_FLAGS) $(SOURCES)

run:
	$(JAVA) $(JAVA_FLAGS) CacheSimulator $(ARGS)

clean:
	rm -r $(CLASSES_DIR)/
