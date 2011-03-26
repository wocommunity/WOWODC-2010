#!/bin/bash
echo "Checking for PATCH_FILE."
if [ "$PATCH_FILE" == "" ]; then
	echo "No PATCH_FILE specified."
else
	# Copy patch file to Wonder directory
	cp ${WORKSPACE}/Patch/${PATCH_FILE} ${WORKSPACE}/Wonder/${PATCH_FILE}
	
	# Patch Wonder
	patch --verbose -p0 -i Wonder/${PATCH_FILE}
fi
