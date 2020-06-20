package ua.citizen.productservice;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderProcessorTest {

//    private static final Logger LOGGER = LoggerFactory.getLogger(OrderReceiverTest.class);
//
//    @Autowired
//    private Processor processor;
//    @Autowired
//    private MessageCollector messageCollector;
//
//    @Test
//    @SuppressWarnings("unchecked")
//    public void testProcessing() {
//        Order o = new Order();
//        o.setId(1L);
//        o.setAccountId(1L);
//        o.setCustomerId(1L);
//        o.setPrice(500);
//        o.setProductIds(Collections.singletonList(2L));
//        processor.input().send(MessageBuilder.withPayload(o).build());
//        Message<Order> received = (Message<Order>) messageCollector.forChannel(processor.output()).poll();
//        LOGGER.info("Order response received: {}", received.getPayload());
//        assertNotNull(received.getPayload());
//        assertEquals(OrderStatus.ACCEPTED, received.getPayload().getStatus());
//    }

}
