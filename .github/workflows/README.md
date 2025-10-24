# GitHub Actions Workflows

This directory contains the CI/CD workflows for the GGUF-LAUNCHER project.

## Build Workflow

**File:** `build.yml`

### Triggers

The build workflow is triggered on:
- Push to `main`, `master`, or `develop` branches
- Pull requests targeting `main`, `master`, or `develop` branches
- Manual workflow dispatch (can be triggered from the Actions tab)

### Jobs

#### Build Job

Runs on: `ubuntu-latest`

**Steps:**

1. **Checkout code** - Checks out the repository code
2. **Set up JDK 17** - Installs Java Development Kit 17 (Temurin distribution)
3. **Setup Android SDK** - Configures the Android SDK environment
4. **Grant execute permission for gradlew** - Makes the Gradle wrapper executable
5. **Cache Gradle packages** - Caches Gradle dependencies to speed up builds
6. **Run Lint** - Executes Android lint checks (continues on error)
7. **Upload Lint Results** - Uploads lint reports as artifacts
8. **Run Unit Tests** - Executes unit tests (continues on error)
9. **Upload Test Results** - Uploads test reports as artifacts
10. **Build Debug APK** - Builds the debug version of the app
11. **Upload Debug APK** - Uploads the debug APK as an artifact
12. **Build Release APK** - Builds the unsigned release version (continues on error)
13. **Upload Release APK** - Uploads the release APK as an artifact

### Artifacts

After each workflow run, the following artifacts are available for download:

- **app-debug** - Debug APK ready for testing
- **app-release-unsigned** - Unsigned release APK (requires signing for distribution)
- **lint-results** - HTML reports from lint checks
- **test-results** - Unit test reports

### Downloading Artifacts

1. Go to the [Actions tab](../../actions)
2. Click on a workflow run
3. Scroll down to the "Artifacts" section
4. Click on the artifact you want to download

### Manual Workflow Trigger

You can manually trigger the build workflow:

1. Go to the [Actions tab](../../actions)
2. Click on "Android CI Build" in the left sidebar
3. Click "Run workflow" button
4. Select the branch you want to build
5. Click "Run workflow"

### Requirements

The workflow requires:
- Java 17 (automatically installed)
- Android SDK (automatically installed)
- Gradle wrapper files (gradlew, gradlew.bat, gradle-wrapper.jar)

### Troubleshooting

**Build fails with "permission denied" for gradlew:**
- The workflow includes a step to make gradlew executable
- Ensure gradlew is committed with execute permissions

**Android SDK components not found:**
- The `android-actions/setup-android@v3` action handles SDK installation
- No additional configuration needed

**Out of memory errors:**
- The project's `gradle.properties` sets JVM args to 2GB
- If needed, this can be increased in the gradle.properties file

**Lint or test failures:**
- These steps use `continue-on-error: true` so they won't fail the build
- Check the uploaded artifacts for detailed reports

### Customization

To modify the workflow:

1. Edit `.github/workflows/build.yml`
2. Commit and push changes
3. The workflow will use the new configuration on the next trigger

Common customizations:
- Add more branches to trigger on
- Change Java version
- Add deployment steps
- Configure signing for release builds
- Add additional build variants
