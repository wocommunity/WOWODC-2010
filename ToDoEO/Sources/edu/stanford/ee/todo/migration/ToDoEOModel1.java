package edu.stanford.ee.todo.migration;

import er.attachment.migrations.ERAttachmentMigration;

public class ToDoEOModel1 extends ERAttachmentMigration {
        
        public ToDoEOModel1(){
                super("FILE_ATTACHMENT", "attachmentId", true);
        }
                
}
