Feature: The user wants to search something on automation practice website

  @Search
  Scenario Outline: Successfully display search result when matches are found
    Given user is on home page
    When user types into the text box the <word>
    Then user should see <results> related to the <word>
    Examples:
      | word   | results                    |
      | Dress  | 5 results have been found. |
      | Blouse | 1 result has been found.   |

  @SortResults
  Scenario Outline: Successfully sort products by lower price
    Given user is on home page
    When user types into the text box the <word>
    And <sort> products by lower price
    Then user should see results ordered lowest to highest price
    Examples:
      | word  | sort                |
      | Dress | Price: Lowest first |

  @AddToShoppingCart
  Scenario Outline: Successfully add product to shopping cart
    Given user is on home page
    When user types into the text box the <word>
    And user add a product to shopping cart
    Then user should see the product <added> to shopping cart
    Examples:
      | word   | added                                            |
      | Blouse | Product successfully added to your shopping cart |

  @ProceedWithPurchase
  Scenario Outline: Successfully proceed with purchase
    Given user is on home page
    When user types into the text box the <word>
    And user add a product to shopping cart
    And goes to shopping cart to proceed with purchase
    Then user should see the authentication step
    Examples:
      | word   |
      | Blouse |

  @ConfirmPurchase
  Scenario Outline: Successfully buy a product
    Given user is on authentication site
    When user creates an account
      | email       | test@yopmail.com |
      | firstname   | John             |
      | lastname    | Taborda          |
      | password    | test123*         |
      | day         | 1                |
      | month       | February         |
      | year        | 1998             |
      | address     | Street 1         |
      | city        | Seattle          |
      | state       | Washington       |
      | zip         | 98101            |
      | mobilephone | +1555555555      |
    And user proceed to checkout
    Then user should see notification <message> to confirm the order
    Examples:
      | message                                                                     |
      | You have chosen to pay by bank wire. Here is a short summary of your order: |