package com.sy.basic.util;
import org.apache.kafka.clients.producer.*;
import org.apache.log4j.Logger;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class NewKafkaSendUtil{
		private static Logger logger = Logger.getLogger(NewKafkaSendUtil.class);
		private static final String BOOTSTRAP_SERVERS_CONFIG = ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
	    private static final String COMPRESSION_TYPE_CONFIG = ProducerConfig.COMPRESSION_TYPE_CONFIG;
	    private static final String ACKS_CONFIG = ProducerConfig.ACKS_CONFIG;
	    private static final String RETRIES_CONFIG = ProducerConfig.RETRIES_CONFIG;
	    private static final String KEY_SERIALIZER_CLASS_CONFIG = ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
	    private static final String VALUE_SERIALIZER_CLASS_CONFIG = ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;
	    private boolean closed =true;
	    private String brokerList = null;
	    private String topic = null;
	    private String compressionType = null;
	    private String securityProtocol = null;
	    private String sslTruststoreLocation = null;
	    private String sslTruststorePassword = null;
	    private String sslKeystoreType = null;
	    private String sslKeystoreLocation = null;
	    private String sslKeystorePassword = null;

	    private int retries = 0;
	    private int requiredNumAcks = 1;//Integer.MAX_VALUE;
	    private boolean syncSend = false;
	    private Producer<byte[], byte[]> producer = null;

	    public Producer<byte[], byte[]> getProducer() {
	        return producer;
	    }

	    public String getBrokerList() {
	        return brokerList;
	    }

	    public void setBrokerList(String brokerList) {
	        this.brokerList = brokerList;
	    }

	    public int getRequiredNumAcks() {
	        return requiredNumAcks;
	    }

	    public void setRequiredNumAcks(int requiredNumAcks) {
	        this.requiredNumAcks = requiredNumAcks;
	    }

	    public int getRetries() {
	        return retries;
	    }

	    public void setRetries(int retries) {
	        this.retries = retries;
	    }

	    public String getCompressionType() {
	        return compressionType;
	    }

	    public void setCompressionType(String compressionType) {
	        this.compressionType = compressionType;
	    }

	    public String getTopic() {
	        return topic;
	    }

	    public void setTopic(String topic) {
	        this.topic = topic;
	    }

	    public boolean getSyncSend() {
	        return syncSend;
	    }

	    public void setSyncSend(boolean syncSend) {
	        this.syncSend = syncSend;
	    }

	    public String getSslTruststorePassword() {
	        return sslTruststorePassword;
	    }

	    public String getSslTruststoreLocation() {
	        return sslTruststoreLocation;
	    }

	    public String getSecurityProtocol() {
	        return securityProtocol;
	    }

	    public void setSecurityProtocol(String securityProtocol) {
	        this.securityProtocol = securityProtocol;
	    }

	    public void setSslTruststoreLocation(String sslTruststoreLocation) {
	        this.sslTruststoreLocation = sslTruststoreLocation;
	    }

	    public void setSslTruststorePassword(String sslTruststorePassword) {
	        this.sslTruststorePassword = sslTruststorePassword;
	    }

	    public void setSslKeystorePassword(String sslKeystorePassword) {
	        this.sslKeystorePassword = sslKeystorePassword;
	    }

	    public void setSslKeystoreType(String sslKeystoreType) {
	        this.sslKeystoreType = sslKeystoreType;
	    }

	    public void setSslKeystoreLocation(String sslKeystoreLocation) {
	        this.sslKeystoreLocation = sslKeystoreLocation;
	    }

	    public String getSslKeystoreLocation() {
	        return sslKeystoreLocation;
	    }

	    public String getSslKeystoreType() {
	        return sslKeystoreType;
	    }

	    public String getSslKeystorePassword() {
	        return sslKeystorePassword;
	    }

	    private static NewKafkaSendUtil instance = null;
	    public static NewKafkaSendUtil getInstance(){
	    	if(NewKafkaSendUtil.instance==null){
	    		NewKafkaSendUtil.instance = new NewKafkaSendUtil();
	    		NewKafkaSendUtil.instance.init();
	    	}	    	
	    	return NewKafkaSendUtil.instance;	    	
	    }
	
	    public void init() {
	        // check for config parameter validity
	    	if(this.closed==false){
	    		return ;
	    	}
	        Properties props = new Properties();
	        
	        if(JsonUtil.getProFile().getProperty("kafka.syncSend")=="false")
	        	this.syncSend = false;
	        else this.syncSend = true;	        
	        props.put(BOOTSTRAP_SERVERS_CONFIG, JsonUtil.getProFile().getProperty("kafka.brokerList"));
	        
	        this.topic = JsonUtil.getProFile().getProperty("kafka.topic");
	        
	        if (compressionType != null)
	            props.put(COMPRESSION_TYPE_CONFIG, compressionType);	 
	        if(JsonUtil.getProFile().containsKey("kafka.requiredNumAcks"))
	        	props.put(ACKS_CONFIG, JsonUtil.getProFile().getProperty("kafka.requiredNumAcks"));
	        
	        if (retries > 0)
	            props.put(RETRIES_CONFIG, retries);
	      

	        props.put(KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
	        props.put(VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
	        this.producer = getKafkaProducer(props);
	        this.closed = false;
	        logger.debug("Kafka producer connected to " + brokerList);
	        logger.debug("Logging for topic: " + topic);
	    }

	    protected Producer<byte[], byte[]> getKafkaProducer(Properties props) {
	        return new KafkaProducer<byte[], byte[]>(props);
	    }
	    public void sendMessage(String message) {	 
	    	init();//确认是ready的
	        Future<RecordMetadata> response = producer.send(new ProducerRecord<byte[], byte[]>(topic, message.getBytes()));
	        if (syncSend) {
	            try {
	                response.get();
	            } catch (InterruptedException ex) {
	                throw new RuntimeException(ex);
	            } catch (ExecutionException ex) {
	                throw new RuntimeException(ex);
	            }
	        }
	    }	    
	    public void close() {
	        if (!this.closed) {
	            this.closed = true;
	            producer.close();
	        }
	    }
}