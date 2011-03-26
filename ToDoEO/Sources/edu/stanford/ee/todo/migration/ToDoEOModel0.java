package edu.stanford.ee.todo.migration;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXMigrationTable;
import er.extensions.migration.ERXModelVersion;

public class ToDoEOModel0 extends ERXMigrationDatabase.Migration {
	@Override
	public NSArray<ERXModelVersion> modelDependencies() {
		return new NSArray<ERXModelVersion>(new ERXModelVersion("ERAttachment", 1));
	}
  
	@Override
	public void downgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
		// DO NOTHING
	}

	@Override
	public void upgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
		ERXMigrationTable userAccountTable = database.newTableNamed("USER_ACCOUNT");
		userAccountTable.newStringColumn("EMAIL", 100, true);
		userAccountTable.newStringColumn("FULL_NAME", 100, false);
		userAccountTable.newIntegerColumn("id", false);
		userAccountTable.newStringColumn("PASSWORD", 100, true);
		userAccountTable.newStringColumn("USERNAME", 100, false);
		userAccountTable.create();
	 	userAccountTable.setPrimaryKey("id");

		ERXMigrationTable fileAttachmentTable = database.newTableNamed("FILE_ATTACHMENT");
		fileAttachmentTable.newIntegerColumn("ATTACHMENT_ID", false);
		fileAttachmentTable.newStringColumn("description", 100, true);
		fileAttachmentTable.newIntegerColumn("id", false);
		fileAttachmentTable.create();
	 	fileAttachmentTable.setPrimaryKey("id");

		ERXMigrationTable todoTable = database.newTableNamed("TODO");
		todoTable.newBooleanColumn("COMPLETED", true);
		todoTable.newTimestampColumn("DATE_CREATED", true);
		todoTable.newTimestampColumn("DUE_DATE", true);
		todoTable.newIntegerColumn("id", false);
		todoTable.newIntegerColumn("OWNER_ID", false);
		todoTable.newStringColumn("TASK", 100, false);
		todoTable.create();
	 	todoTable.setPrimaryKey("id");

		ERXMigrationTable todoFileAttachmentTable = database.newTableNamed("TodoFileAttachment");
		todoFileAttachmentTable.newIntegerColumn("fileAttachmentId", false);
		todoFileAttachmentTable.newIntegerColumn("todoId", false);
		todoFileAttachmentTable.create();
	 	todoFileAttachmentTable.setPrimaryKey("fileAttachmentId", "todoId");

		fileAttachmentTable.addForeignKey("ATTACHMENT_ID", "ERAttachment", "id");
		todoTable.addForeignKey("OWNER_ID", "USER_ACCOUNT", "id");
		todoFileAttachmentTable.addForeignKey("fileAttachmentId", "FILE_ATTACHMENT", "id");
		todoFileAttachmentTable.addForeignKey("todoId", "TODO", "id");
	}
}