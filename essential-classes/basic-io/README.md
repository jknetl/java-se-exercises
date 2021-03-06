# Basic IO

This module contains an exercises for the [Basic I/O Java trail](https://docs.oracle.com/javase/tutorial/essential/io/index.html).

## List of features covered by exercises

### Basic I/O

* Streams
  * Byte streams
  * Character streams
  * Converting byte stream to character stream using a bridge
  * Buffering
  * Data streams - read and write
     * Try to write a type and try to read it as different type
  * Object streams
* Scanning
  * Tokenizing by scanner
  * Translating a tokens to proper value
  * Locale change example
* Formatting
  * PrintWriter and PrintStream classes and their subclasses
  * Format() method
* IO from command line
  * Standard streams
  * Console object

## File I/O
* Path class
  * Creating, comparing, testing on existence, symbolic links
  * Retrieving part of path
  * Merging paths
  * Normalizing path
  * Converting path to URI, Absolute, Real
* File operations
  * Releasing resources - closable
  * Atomic operations
  * Glob
  * Checking for file and directory
    * Two paths same target?
  * Deleting files and dirs
  * Copying
  * Moving
* Managing metdata
  * Basic attributes
  * Posix attributes
  * User-defined attributes
* Read,write and create files
* Use random access files
* Reading directories
  * + filtering listing using globbing
  * + filtering listing using own filter
* Creating symbolic link and hardlink, and finding its target
* Walking file tree with visitor
* FInding files using visitor
* Watching a directory for changes
* Mime types
* Checking filesystem size and root(s)

## Exercises

### Basic I/O

#### exercise 1
Write a program which will read some text file A and will write the file to buffered character stream. Then it will read the same file using
unbuffered IO and write ouput into byte stream.

#### exercise 2
Write a program which will create own type of Character stream on top of InputStream using a bridge, It will be decrypting stream using simple ceasar cipher.
In order to verify that your stream decrypts correctly you can try decrypt file `src/main/resources/encrypted` with shift 7 (you should see meaningful text).
Note that encrypted text encrypts only alphabetic characters.
Implement also encrypting stream on top of Output stream using a bridge. Then write encrypted message using stream. Then read it using simple file reader, you should get encrypted text.

#### exercise 3
Write program which will store simple data types into data stream. Implement a program which will read primitive data types from stream.
 Then try to read stream again, but try to read types in wrong order. Observe what happens.

#### exercise 4
Write Program which will serialize few objects. Verify that if you store instance multiple times and you read it afterwards,
 that you will get exactly same instance (== comparison will return true)

#### exercise 5
Create a file which will consists of numbers, decimal numbers, and strings in it.
 Use Scanner to tokenize it and also to read different types. Then use scanner with different locale, you should get different result.

#### exercise 6
Create a program which demonstrates basic usage of PrintWriter's format method.

#### exercise 7
Create a simple unix like “cat” program using standard streams

#### exercise 8
Create a simple unix like “cat” program using the Console class.

### File I/O

#### exercise 1
Manually create a directory which will contain another two subdirectories, one file, one symlink and one hardlink.
Try retrieving path, merging paths, normalizing paths, testing on existence and links.

#### exercise 2
Create program which will behave as both as “cp” and “mv”.

#### exercise 3
Create a “rm” program. It should work on directories, files and links.

#### exercise 4
Create a program which will allow you to show basic attributes.

#### exercise 5
Create a program which will be able to set posix permissions.

#### exercise 6
Create a program which will be able to set owner of a file.

#### exercise 7
Create a program which will be able to store and read user-defined attributes into files metadata.

#### exercise 8
Create a program which will use java.nio package to read one file and write it to another file.
It will dynamically determine the file size. If the size is larger than some threshold,
then it will use streams, otherwise will use methods designed for small files
 
#### exercise 9
Seek inside of Random access file and write some string to the middle of a file.

#### exercise 10
Create a “ls” program which will simply show content of the directories. It will allow to take glob filter. If no directory is passed
as argument, then first root filesystem root will be used.

#### exercise 11
Write program which will create a symlink and hardlink to specific file.

#### exercise 12
Create simple “find” program, which will find files by name (and possibly by other attribute)

#### exercise 13
Create program which will watch a directory for changes. If some file in the directory changes it will be printed to stdout.

