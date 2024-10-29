### testing strategy

always use the test profile in every stage

1) test jpa layer using @DataJpaTest 
2) test service layer using Mockito
3) test controller layer using @WebMvcTest
4) test whole workflow example using @SpringBootTest