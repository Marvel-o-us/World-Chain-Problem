# Word Chain Problem
My solution to the Word Chain Problem

### Introduction
This is a type of puzzle where the challenge is to build a chain of words, starting with one particular word and ending with another. Successive entries in the chain must all be real words, and each can differ from the previous word by just one letter. For example, you can get from “cat” to “dog” using the following chain.

```
cat
cot
cog
dog
```

### Prerequisites

Java 1.7 or above

### Executing

#### With the provided Dictionary
Dictionary (`Dictionary.txt`) has already been provided with the code. So to check the code with current dictionary, simply execute (in the *src* directory) : `javac Main.java` and then `java Main.class`

#### With a new Dictionary
Delete the current `Dictionary.txt` and create a new file named the same containing words from the new dictionary (*each word must be in a separate line*) and then execute : 
```
javac CreateDictionary.java
java CreateDictionary.class
javac Main.java
java Main.class
``` 
The CreateDictionary.java file arranges same length words into same files named as `x_word_dictionary.txt` where x is the length of each word in the file.
