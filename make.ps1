param (
    [parameter(Position=0)]
    [ValidateSet('stack', 'fe', 'be')]
    [String]$stackOrFrontend=$(throw "Full stack (stack), Backend (be), or Frontend (fe)? Example: .\make.ps1 stack build"),

    [parameter(Position=1)]
    [ValidateSet('build', 'run')]
    [String]$buildOrRun=$(throw "Build (build) or Run (run)? Example: .\make.ps1 stack run")
)

$currentDir = Split-Path $MyInvocation.MyCommand.Path

$buildStack    = 'docker build -f Dockerfile-full -t react-server-full .'
$buildFrontend = 'docker build -f Dockerfile-fe -t react-server-fe .'
$buildBackend  = 'docker build -f Dockerfile-be -t react-server-be .'
$runStack      = 'docker run -p 8080:8080 --name stack --rm react-server-full'
$runFrontend   = 'docker run -p 8080:8080 -it --name frontend --rm -v ' + $currentDir + '\src\main\frontend:/app react-server-fe bash'
$runBackend    = 'docker run -p 8080:8080 -it --name backend --rm -v ' + $currentDir + ':/app react-server-be bash'

if ($buildOrRun -eq 'build' -And $stackOrFrontend -eq 'stack') {
    Write-Output $buildStack
    Invoke-Expression $buildStack
} elseif ($buildOrRun -eq 'build' -And $stackOrFrontend -eq 'fe') {
    Write-Output $buildFrontend
    Invoke-Expression $buildFrontend
} elseif ($buildOrRun -eq 'build' -And $stackOrFrontend -eq 'be') {
    Write-Output $buildBackend
    Invoke-Expression $buildBackend
} elseif ($buildOrRun -eq 'run' -And $stackOrFrontend -eq 'stack') {
    Write-Output $runStack
    Invoke-Expression $runStack
} elseif ($buildOrRun -eq 'run' -And $stackOrFrontend -eq 'fe') {
    Write-Output $runFrontend
    Invoke-Expression $runFrontend
} elseif ($buildOrRun -eq 'run' -And $stackOrFrontend -eq 'be') {
    Write-Output $runBackend
    Invoke-Expression $runBackend
} else {
    Write-Output 'Yolo!'
}
