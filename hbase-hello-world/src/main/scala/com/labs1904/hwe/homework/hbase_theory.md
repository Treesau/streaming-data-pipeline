# Overview

By now you've seen some different Big Data frameworks such as Kafka and Spark. Now we'll be focusing in on HBase. In this homework, your
challenge is to write answers that make sense to you, and most importantly, **in your own words!**
Two of the best skills you can get from this class are to find answers to your questions using any means possible, and to
reword confusing descriptions in a way that makes sense to you. 

### Tips
* You don't need to write novels, just write enough that you feel like you've fully answered the question
* Use the helpful resources that we post next to the questions as a starting point, but carve your own path by searching on Google, YouTube, books in a library, etc to get answers!
* We're here if you need us. Reach out anytime if you want to ask deeper questions about a topic 
* This file is a markdown file. We don't expect you to do any fancy markdown, but you're welcome to format however you like


### Your Challenge
1. Create a new branch for your answers 
2. Complete all of the questions below by writing your answers under each question
3. Commit your changes and push to your forked repository

## Questions
#### What is a NoSQL database? 
It's a database that operates differently from SQL, 
generally non-relational.

#### In your own words, what is Apache HBase? 
HBase is a NoSQl database designed to overcome the limitations of the Hadoop Distributed File System,
which it is built on top of. It is open source, fault-tolerant, and horizontally scalable. It allows faster
random read/writes than HDFS.

#### What are some strengths and limitations of HBase? 
* [HBase By Examples](https://sparkbyexamples.com/apache-hbase-tutorial/)
HBase allows storage of very large datasets and is easily scalable. It's also highly fault-tolerant for storing sparse data.
Unfortunately, HBase is relatively difficult to query and doesn't allow join operations.

#### Explain the following concepts: 
* Rowkey
Unique label for a row
* Column Qualifier
Unique label for a column
* Column Family
Columns stored on the same physical memory/disk

#### What are the differences between Get and Put commands in HBase? 
* [HBase commands](https://www.tutorialspoint.com/hbase/hbase_create_data.htm)
Get commands read data from database. Put commands insert or update existing data.

#### What is the HBase Scan command for? 
* [HBase Scan](https://www.tutorialspoint.com/hbase/hbase_scan.htm)
Similar to Get but for multiple rows, can retrieve entire table

#### What was the most interesting aspect of HBase when went through all the questions? 
Coming from an exclusively relational DB background, HBase's "distributed map" structure was interesting, 
that rows can have completely unique sets of columns.