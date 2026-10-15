public final class Customer {
    private final String customerId;
    private String name;
    private String membershipType;

    public Customer(String customerId, String name, String membershipType) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty.");
        }

        this.customerId = customerId;
        this.name = name;
        setMembershipType(membershipType);
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        if (membershipType == null) {
            throw new IllegalArgumentException("Membership type is required.");
        }

        if (membershipType.equalsIgnoreCase("VIP")) {
            this.membershipType = "VIP";
        } else if (membershipType.equalsIgnoreCase("Regular")) {
            this.membershipType = "Regular";
        } else {
            throw new IllegalArgumentException(
                    "Membership must be Regular or VIP."
            );
        }
    }

    public double calculateDiscount(double totalAmount) {
        if (membershipType.equals("VIP")) {
            return totalAmount * 0.10;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return customerId + " - " + name
                + " - Membership: " + membershipType;
    }
}

