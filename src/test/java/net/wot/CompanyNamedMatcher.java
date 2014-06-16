package net.wot;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CompanyNamedMatcher extends TypeSafeMatcher<Company> {
	
	private final String expectedCompanyName;
	
	private CompanyNamedMatcher(String companyName) {
		super();
		this.expectedCompanyName = companyName;
	}
	
	public static CompanyNamedMatcher companyNamed(String companyName) {
		return new CompanyNamedMatcher(companyName);
	}

	@Override
	protected boolean matchesSafely(Company actual) {
		return expectedCompanyName.equals(actual.getName());
	}

	@Override
	public void describeTo(Description matchDescription) {
		matchDescription.appendText("a company named ")
						.appendValue(expectedCompanyName);
	}

	@Override
	protected void describeMismatchSafely(Company actual,
			Description mismatchDescription) {
		mismatchDescription.appendText(" was ")
		           		   .appendValue(actual.getName());
	}

}
