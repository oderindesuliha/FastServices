package org.group6.fastservices.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizations")
@CrossOrigin(origins = "*")
public class OrganizationController {
    
//    @Autowired
//    private OrganizationService organizationService;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostMapping("/register")
//    public ResponseEntity<?> registerOrganization(@RequestBody OrganizationRequest organizationRequest){
//        try {
//            // First create a user account for the organization
//            User orgUser = new User();
//            orgUser.setFirstName(organizationRequest.getName());
//            orgUser.setLastName("Admin"); // Default last name for organization admin
//            orgUser.setEmail(organizationRequest.getContactEmail());
//            orgUser.setPassword(passwordEncoder.encode(organizationRequest.getPassword()));
//            orgUser.setPhone(organizationRequest.getContactPhone());
//
//            // Set role as ORGANIZATION
//            Set<Role> roles = new HashSet<>();
//            roles.add(Role.ORGANIZATION);
//            orgUser.setRoles(roles);
//
//            // Save the user
//            User savedUser = userService.createUser(orgUser);
//
//            // Then create the organization entity
//            Organization organization = new Organization();
//            // Copy properties except password
//            BeanUtils.copyProperties(organizationRequest, organization, "password");
//
//            // Generate a unique code based on organization name
//            String uniqueCode = generateUniqueCode(organizationRequest.getName());
//            organization.setCode(uniqueCode);
//
//            Organization savedOrganization = organizationService.createOrganization(organization);
//
//            OrganizationResponse organizationResponse = Mapper.mapToOrganizationResponse(savedOrganization);
//            return ResponseEntity.status(HttpStatus.CREATED).body(organizationResponse);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ErrorResponse<>(e.getMessage()));
//        }
//    }
//
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getOrganizationById(@PathVariable String id) {
//        try {
//            Organization organization = organizationService.getOrganizationById(id);
//            OrganizationResponse organizationResponse = Mapper.mapToOrganizationResponse(organization);
//            return ResponseEntity.ok(organizationResponse);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ErrorResponse<>(e.getMessage()));
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<?> getAllOrganizations() {
//        try {
//            List<Organization> organizations = organizationService.getAllOrganizations();
//            List<OrganizationResponse> organizationResponses = organizations.stream()
//                    .map(Mapper::mapToOrganizationResponse)
//                    .collect(Collectors.toList());
//
//            return ResponseEntity.ok(organizationResponses);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ErrorResponse<>(e.getMessage()));
//        }
//    }
//
//    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<?> updateOrganization(@PathVariable String id, @RequestBody OrganizationRequest organizationRequest) {
//        try {
//            Organization organization = new Organization();
//            BeanUtils.copyProperties(organizationRequest, organization);
//            Organization updatedOrganization = organizationService.updateOrganization(id, organization);
//
//            OrganizationResponse organizationResponse = Mapper.mapToOrganizationResponse(updatedOrganization);
//            return ResponseEntity.ok(organizationResponse);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ErrorResponse<>(e.getMessage()));
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<?> deleteOrganization(@PathVariable String id) {
//        try {
//            organizationService.deleteOrganization(id);
//            return ResponseEntity.noContent().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ErrorResponse<>(e.getMessage()));
//        }
//    }
//
//    @GetMapping("/code/{code}")
//    public ResponseEntity<?> getOrganizationByCode(@PathVariable String code) {
//        try {
//            Organization organization = organizationService.getOrganizationByCode(code);
//            OrganizationResponse organizationResponse = Mapper.mapToOrganizationResponse(organization);
//            return ResponseEntity.ok(organizationResponse);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ErrorResponse<>(e.getMessage()));
//        }
//    }
//
//    // Generate a unique code based on organization name
//    private String generateUniqueCode(String organizationName) {
//        // Extract first 3 characters from organization type in the name
//        String prefix = "ORG"; // Default prefix
//
//        if (organizationName != null) {
//            String upperName = organizationName.toUpperCase();
//            if (upperName.contains("HOSPITAL")) {
//                prefix = "HOS";
//            } else if (upperName.contains("BANK")) {
//                prefix = "BAN";
//            } else if (upperName.contains("SCHOOL")) {
//                prefix = "SCH";
//            } else if (upperName.contains("UNIVERSITY")) {
//                prefix = "UNI";
//            } else if (upperName.contains("GOVERNMENT")) {
//                prefix = "GOV";
//            } else {
//
//                prefix = upperName.length() >= 3 ? upperName.substring(0, 3) : "ORG";
//            }
//        }
//
//        // Add a sequential number (in a real app, you'd check the database for the next available number)
//        String number = "001"; // This should be dynamically generated based on existing records
//
//        return prefix + number;
//    }
}