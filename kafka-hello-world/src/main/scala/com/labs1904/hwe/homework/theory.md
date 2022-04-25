# Overview

Kafka has many moving pieces, but also has a ton of helpful resources to learn available online. In this homework, your
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
#### What problem does Kafka help solve? Use a specific use case in your answer
* Helpful resource: [Confluent Motivations and Use Cases](https://youtu.be/BsojaA1XnpM)
Some video game developers are starting to use Kafka to stream local events to be processed remotely for more advanced NPC AI and physics.


#### What is Kafka?
* Helpful resource: [Kafka in 6 minutes](https://youtu.be/Ch5VhJzaoaI) 
Kafka is an open-source event-driven message streaming system. It can be run locally or distributed (cluster). 
Kafka requires a "distribution strategy" for placing records into its queues (partitions). This can ensure messages are processed by consumers in the correct order.

#### Describe each of the following with an example of how they all fit together:
 * Topic
 * Producer
 * Consumer
 * Broker
 * Partition

Let's say we have a financial application that records user payments and sends each transaction to a Kafka stream.
This application would be called a "Producer", as it provides data/events for Kafka to store.
Kafka distributes these messages to "Partitions" running on sometimes multiple physical servers, called "Brokers".
Partitions that handle the same type of data are called a "Topic". When we want to get our payment data back from Kafka, 
we will use another application called a "Consumer" to read messages from the stream and potentially process them.

#### Describe Kafka Producers and Consumers
Producers are applications that publish messages to a Kafka cluster.
Consumers are applications that read messages from a Kafka cluster.

#### How are consumers and consumer groups different in Kafka?
* Helpful resource: [Consumers](https://youtu.be/lAdG16KaHLs)
* Helpful resource: [Confluent Consumer Overview](https://youtu.be/Z9g4jMQwog0)

#### How are Kafka offsets different than partitions?
An offset is a unique sequential number pointing to each record on a partition.

#### How is data assigned to a specific partition in Kafka?
If a partition key is specified by the producer application, it is appended to that partition. 
Otherwise, Kafka chooses a partition at random.

#### Describe immutability - Is data on a Kafka topic immutable?
Immutable means something cannot be changed. Kafka data is immutable, as any record cannot be updated or overwritten.

#### How is data replicated across brokers in kafka? If you have a replication factor of 3 and 3 brokers, explain how data is spread across brokers
* Helpful resource [Brokers and Replication factors](https://youtu.be/ZOU7PJWZU9w)
A leader broker receives and serves data for a particular partition. 
Then, replica brokers sync with their leader's data. 
A replication factor of 3 on 3 brokers utilizes 1 broker as the topic leader and the other 2 as replicas.

#### What was the most fascinating aspect of Kafka to you while learning? 
How simple it is architecturally--just appending to the end of looong logs--while performing in real-time on enormous datasets.
Of course, we barely scraped the surface of how it all works, but I didn't expect it to be so intuitive at a high level.
