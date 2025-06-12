package ir.ac.kntu;

import java.time.LocalDate;
import java.time.Period;

public class VendiloPlus {
    private LocalDate expirationDate;

    public VendiloPlus() {
    }

    public VendiloPlus(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void extendSubscription(Period period) {
        if (expirationDate == null || LocalDate.now().isAfter(expirationDate)) {
            expirationDate = LocalDate.now().plus(period);
        } else {
            expirationDate = expirationDate.plus(period);
        }
    }

    public boolean isActive() {
        return expirationDate != null && LocalDate.now().isBefore(expirationDate);
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        if (expirationDate == null) {
            return "Vandilo Plus is not active.";
        }

        if (LocalDate.now().isAfter(expirationDate)) {
            return "Vandilo Plus expired on: " + expirationDate;
        }

        Period remaining = Period.between(LocalDate.now(), expirationDate);
        return "Vandilo Plus active until: " + expirationDate +
                "\n(" + remaining.getYears() + "years and " + remaining.getMonths() + " months and " + remaining.getDays() + " days remaining)";
    }

}
