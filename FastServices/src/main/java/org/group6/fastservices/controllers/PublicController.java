package org.group6.fastservices.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "*")
public class PublicController {

//    private final OrganizationService organizationService;
//    private final OfferingService offeringService;
//
//    @Autowired
//    public PublicController(OrganizationService organizationService, OfferingService offeringService) {
//        this.organizationService = organizationService;
//        this.offeringService = offeringService;
//    }
//
//    // Get organization by ID (for QR code scanning)
//    @GetMapping("/organizations/{id}")
//    public ResponseEntity<?> getOrganizationById(@PathVariable String id) {
//        try {
//            Organization organization = organizationService.getOrganizationById(id);
//            if (organization == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(new ErrorResponse<>("Organization not found with id: " + id));
//            }
//
//            OrganizationResponse organizationResponse = Mapper.mapToOrganizationResponse(organization);
//            return ResponseEntity.ok(organizationResponse);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ErrorResponse<>(e.getMessage()));
//        }
//    }
//
//    // Get all offerings for an organization (for QR code scanning)
//    @GetMapping("/organizations/{id}/offerings")
//    public ResponseEntity<?> getOfferingsByOrganizationId(@PathVariable String id) {
//        try {
//            List<Offering> offerings = offeringService.getOfferingsByOrganizationId(id);
//            List<OfferingResponse> serviceResponses = offerings.stream()
//                    .map(Mapper::mapToOfferingResponse)
//                    .collect(Collectors.toList());
//
//            return ResponseEntity.ok(serviceResponses);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ErrorResponse<>(e.getMessage()));
//        }
//    }
//
//    // Get a specific offering
//    @GetMapping("/offerings/{id}")
//    public ResponseEntity<?> getOfferingById(@PathVariable String id) {
//        try {
//            Offering offering = offeringService.getOfferingById(id);
//            if (offering == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(new ErrorResponse<>("Offering not found with id: " + id));
//            }
//
//            OfferingResponse serviceResponse = Mapper.mapToOfferingResponse(offering);
//            return ResponseEntity.ok(serviceResponse);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ErrorResponse<>(e.getMessage()));
//        }
//    }
}