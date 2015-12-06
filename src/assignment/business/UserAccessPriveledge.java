package assignment.business;

public enum UserAccessPriveledge {
	GUEST, MEMBER, MODERATOR, CONTENT_MANAGER, BANNED;
	
	
	
	public String toString() {
		switch (this) {
		case GUEST: return "Guest";
		case MEMBER: return "Member";
		case MODERATOR: return "Moderator";
		case CONTENT_MANAGER: return "ContentManager";
		case BANNED: return "Banned";
		default: return "";
		}
	}
	
	
	/**
	 * Returns a UserAccessPriveldge from its string representation
	 * @param priveledge The string.
	 * @return A new {@link UserAccessPriveledge}, if an unknown string is provided
	 * null will be returned.
	 **/
	public static UserAccessPriveledge stringForPriveledge(String priveledge) {
		switch (priveledge) {
		case "Guest": return UserAccessPriveledge.GUEST;
		case "Member": return UserAccessPriveledge.MEMBER;
		case "Moderator": return UserAccessPriveledge.MODERATOR;
		case "ContentManager": return UserAccessPriveledge.CONTENT_MANAGER;
		case "Banned": return UserAccessPriveledge.BANNED;
		default: return null;
		}
	}
}
