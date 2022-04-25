package com.labs1904.hwe.consumers

import com.labs1904.hwe.producers.SimpleProducer
import com.labs1904.hwe.util.Util
import com.labs1904.hwe.util.Util.getScramAuthString
import net.liftweb.json.DefaultFormats
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringDeserializer

import java.time.Duration
import java.util.{Arrays, Properties, UUID}

case class RawUser(id: Int, name: String, email: String)
case class EnrichedUser(id: Int, numberAsWord: String, name: String, email: String, hweDeveloper: String)

object HweConsumer {
  val BootstrapServer : String = "CHANGEME"
  val consumerTopic: String = "question-1"
  val producerTopic: String = "question-1-output"
  val username: String = "CHANGEME"
  val password: String = "CHANGEME"
  //Use this for Windows
  val trustStore: String = "src\\main\\resources\\kafka.client.truststore.jks"
  //Use this for Mac
  //val trustStore: String = "src/main/resources/kafka.client.truststore.jks"

  implicit val formats: DefaultFormats.type = DefaultFormats

  def main(args: Array[String]): Unit = {

    // Create the KafkaConsumer
    val consumerProperties = SimpleConsumer.getProperties(BootstrapServer)
    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](consumerProperties)

    // Create the KafkaProducer
    val producerProperties = SimpleProducer.getProperties(BootstrapServer)
    val producer = new KafkaProducer[String, String](producerProperties)

    // Subscribe to the topic
    consumer.subscribe(Arrays.asList(consumerTopic))

    while ( {
      true
    }) {
      // poll for new data
      val duration: Duration = Duration.ofMillis(100)
      val records: ConsumerRecords[String, String] = consumer.poll(duration)

      records.forEach((record: ConsumerRecord[String, String]) => {
        // Retrieve the message from each record
        val message = record.value()
        println(s"Message Received: $message")
        // TODO: Add business logic here!
        // Split message lines by comma
        val splitMsg = message.split(',')
        val (id, name, email) = (splitMsg(0).toInt, splitMsg(1), splitMsg(2))

        // Create instance of RawUser
        val rawUser = RawUser(id, name, email)

        // EnrichedUser
        val hweDev = "Trenton Sauer"
        val enrichedUser = EnrichedUser(id, Util.mapNumberToWord(rawUser.id), name, email, hweDev)

        // Convert enriched user to comma separated message and write to Kafka
        val enrichedUserString = s"${enrichedUser.id}, ${enrichedUser.numberAsWord}, ${enrichedUser.name}, ${enrichedUser.email}, ${enrichedUser.hweDeveloper}"
        val enrichedUserMessage = new ProducerRecord[String, String](producerTopic, enrichedUserString)
        producer.send(enrichedUserMessage)

      })
    }
  }
}