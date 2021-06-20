package br.com.marceloazevedo.dentist.odonto.api.config

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableDynamoDBRepositories(
        "br/com/marceloazevedo/dentist/odonto/api/repository"
)
class DynamoDBConfig {

    @Value("\${amazon.dynamodb.endpoint}")
    private lateinit var amazonDynamoDBEndpoint: String

    @Value("\${amazon.aws.accesskey}")
    private lateinit var amazonAWSAccessKey: String

    @Value("\${amazon.aws.secretkey}")
    private lateinit var amazonAWSSecretKey: String

    @Value("\${amazon.aws.region}")
    private lateinit var amazonRegion: String

    @Bean
    fun amazonDynamoDB(): AmazonDynamoDB? =
            AmazonDynamoDBClientBuilder
                    .standard()
                    .withCredentials(AWSStaticCredentialsProvider(amazonAWSCredentials()))
                    .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, amazonRegion))
                    .build()

    @Bean
    fun amazonAWSCredentials(): AWSCredentials? =
            BasicAWSCredentials(
                    amazonAWSAccessKey, amazonAWSSecretKey)

}