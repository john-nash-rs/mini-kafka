# mini-kafka

## How to learn about kafka internals?

Let's make a mini kafka. Please follow the steps below and you should be able to many of the kafka  concepts.

### Step 1

Start a TCP server. Send and receive "Hello world".

Look at class [ Application ](https://github.com/john-nash-rs/mini-kafka/blob/main/src/main/java/server/Application.java) 

More resource to create [TCP server](https://www.baeldung.com/a-guide-to-java-sockets)

Please make sure that your code can handle multiple TCP connection at once. 

### Step 2

Add Context class.
This class parses instructions and stores operationa and args in context class.

Example: create topic --partition=2 --replica=2

Operation is create topic. And argument is parition and replica. 