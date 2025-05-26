package ir.ac.kntu;

public class AddressOption {
    private Address address;
    private boolean isAddNew;

    public AddressOption(Address address) {
        this.address = address;
        this.isAddNew = false;
    }

    public AddressOption() {
        this.isAddNew = true;
    }

    public boolean isAddNew() {
        return isAddNew;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return isAddNew ? "Add new address" : address.toString();
    }
}
