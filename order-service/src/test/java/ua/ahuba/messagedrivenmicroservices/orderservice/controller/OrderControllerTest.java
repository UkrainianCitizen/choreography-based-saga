package ua.ahuba.messagedrivenmicroservices.orderservice.controller;

public class OrderControllerTest {

//    TestRestTemplate template = new TestRestTemplate();
//
//    @Test
//    public void testOrder() throws InterruptedException {
//        for (int i = 0; i < 100; i++) {
//            sendAndAcceptOrder();
//            Thread.sleep(5000);
//        }
//    }
//
//    private void sendAndAcceptOrder() {
//        try {
//            Random r = new Random();
//            Order order = new Order();
//            order.setCustomerId((long) r.nextInt(3) + 1);
//            order.setProductIds(Arrays.asList(new Long[] { (long) r.nextInt(10) + 1, (long) r.nextInt(10) + 1 }));
//            order = template.postForObject("http://localhost:8090", order, Order.class);
//            if (order.getStatus() != OrderStatus.REJECTED) {
//                template.put("http://localhost:8090/{id}", null, order.getId());
//            }
//        } catch (Exception e) {
//
//        }
//    }

}
