=====================================================================================

Pharmacy
-------

-- Receive the prescription from the patient

The patient gives the prescription to the pharmacist

When I create a prescription with a drug name, quantity, patient's name, doctor name 
Then I can retrieve the drug name, quantity, patient's name, doctor name


-- Check if the medication is available or not

Given there is a drug available
When I check the quantity of the medication
Then I should see the current medication quantity

Given I there is a drug not available
When I enter a non-existent drug name
Then I should see no such drug exists

-- Check validity of the prescription from the doctor prescription list

Given there is a drug available
When I check prescription is in the doctor prescription list
Then I should see a message "prescription is valid" 

Given there is a drug available
When I check prescription is not in the doctor prescription list
Then I should see a message "Can't dispense the medication" 

-- Dispense the medication

When I dispense the medication from the pharmacy
Then I should see the drug quantity should decrease

-- Prescription status changes to ready state to pick up

When the medication from the pharmacy is dispensed
Then the prescription status shows "READY"

-- When the patient pick up the medication the status changes to sold state

When I sold the medication from the pharmacy
Then I should see the pharmacy amount balance from sales should increase

When I sold the medication from the pharmacy
Then the prescription status shows "SOLD"

When I cancel the prescription order
Then I get the full amount refunded

=====================================================================================