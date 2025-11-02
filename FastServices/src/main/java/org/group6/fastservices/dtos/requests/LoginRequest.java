package org.group6.fastservices.dtos.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class LoginRequest {

    private String identifier;
    private String password;
    private String role;
}


//@SpringBootTest
//class QueueServiceImplTest {
//
//    @Autowired
//    private QueueService queueService;
//    @Autowired
//    private QueueRepository queueRepository;
//    @Autowired
//    private OfferingRepository offeringRepository;
//    @Autowired
//    private OrganizationRepository organizationRepository;
//
//    private Offering offering;
//
//    @BeforeEach
//    void setup() {
//        queueRepository.deleteAll();
//        offeringRepository.deleteAll();
//        organizationRepository.deleteAll();
//
//        Organization org = new Organization();
//        org.setName("FAST Uni");
//        org.setCode("FAST001");
//        org.setContactEmail("org@mail.com");
//        org.setContactPhone("0800000000");
//        org.setPassword("encoded");
//        org.setRole("ORGANIZATION");
//        Organization savedOrg = organizationRepository.save(org);
//
//        Offering newOffering = new Offering();
//        newOffering.setName("Transcript Request");
//        newOffering.setDescription("Get academic transcript");
//        newOffering.setOrganization(savedOrg);
//        offering = offeringRepository.save(newOffering);
//    }
//
//    @Test
//    void testFindOrCreateQueueForOffering_createsNewQueue() {
//        Queue queue = queueService.findOrCreateQueueForOffering(offering);
//
//        assertNotNull(queue);
//        assertEquals(offering.getId(), queue.getOffering().getId());
//        assertTrue(queueRepository.findByOfferingId(offering.getId()).isPresent());
//    }
//
//    @Test
//    void testFindOrCreateQueueForOffering_reusesExistingQueue() {
//        Queue first = queueService.findOrCreateQueueForOffering(offering);
//        Queue second = queueService.findOrCreateQueueForOffering(offering);
//
//        assertEquals(first.getId(), second.getId());
//        assertEquals(1, queueRepository.count());
//    }
//}




//@Service
//@AllArgsConstructor
//public class AppointmentServiceImpl implements AppointmentService {
//
//    private final CustomerRepository customerRepository;
//    private final AppointmentRepository appointmentRepository;
//    private final OfferingRepository offeringRepository;
//    private final QueueService queueService; // Injected
//    private final ModelMapper modelMapper;
//
//    @Override
//    @PreAuthorize("hasRole('CUSTOMER')")
//    @Transactional
//    public CreateAppointmentResponse createAppointment(CreateAppointmentRequest request) {
//        Customer customer = getAuthenticatedCustomer();
//
//        Offering offering = offeringRepository.findOfferingByName(request.getOfferingName())
//                .orElseThrow(() -> new OfferingNotFoundException("Service not found"));
//
//        Queue queue = queueService.findOrCreateQueueForOffering(offering);
//        int position = queue.getAppointments().size() + 1;
//
//        Appointment appointment = modelMapper.map(request, Appointment.class);
//        appointment.setUser(customer);
//        appointment.setOffering(offering);
//        appointment.setQueue(queue);
//        appointment.setStatus(AppointmentStatus.PENDING);
//        appointment.setQueuePosition(position);
//        appointment.setCreatedAt(LocalDateTime.now());
//
//        appointmentRepository.save(appointment);
//
//        return new CreateAppointmentResponse(
//                appointment.getAppointmentDate(),
//                appointment.getStatus(),
//                position,
//                appointment.getCreatedAt(),
//                "Appointment created successfully",
//                true
//        );
//    }
//
//    // other methods unchanged...
//}


//@SpringBootTest
//class AppointmentServiceImplTest {
//
//    @Autowired
//    private AppointmentService appointmentService;
//    @Autowired
//    private OfferingRepository offeringRepository;
//    @Autowired
//    private OrganizationRepository organizationRepository;
//    @Autowired
//    private CustomerRepository customerRepository;
//    @Autowired
//    private QueueRepository queueRepository;
//
//    private Customer savedCustomer;
//    private Offering savedOffering;
//
//    @BeforeEach
//    void setup() {
//        queueRepository.deleteAll();
//        offeringRepository.deleteAll();
//        organizationRepository.deleteAll();
//        customerRepository.deleteAll();
//
//        Organization org = new Organization();
//        org.setName("FAST Uni");
//        org.setCode("FAST001");
//        org.setContactEmail("org@mail.com");
//        org.setContactPhone("0800000000");
//        org.setPassword("encoded");
//        org.setRole("ORGANIZATION");
//        organizationRepository.save(org);
//
//        Offering offering = new Offering();
//        offering.setName("Library Access");
//        offering.setDescription("Get library card");
//        offering.setOrganization(org);
//        savedOffering = offeringRepository.save(offering);
//
//        Customer customer = new Customer();
//        customer.setEmail("user@mail.com");
//        customer.setPassword("encoded");
//        savedCustomer = customerRepository.save(customer);
//
//        // Mock authentication
//        AuthenticatedPrincipal principal = new AuthenticatedPrincipal(savedCustomer);
//        UsernamePasswordAuthenticationToken auth =
//                new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(auth);
//    }
//
//    @Test
//    @WithMockUser(roles = {"CUSTOMER"})
//    void testCreateAppointment_createsQueueAutomatically() {
//        CreateAppointmentRequest request = new CreateAppointmentRequest();
//        request.setOfferingName(savedOffering.getName());
//        request.setAppointmentDate(LocalDateTime.now().plusDays(1));
//
//        var response = appointmentService.createAppointment(request);
//
//        assertTrue(response.isSuccess());
//        assertEquals("Appointment created successfully", response.getMessage());
//        assertEquals(1, queueRepository.count());
//    }
//}
