param (
    [parameter(Position=0)]
    [ValidateSet('stack', 'fe')]
    [String]$stackOrFrontend=$(throw "Full stack (stack) or Frontend (fe)? Example: .\make.ps1 stack build"),

    [parameter(Position=1)]
    [ValidateSet('build', 'run')]
    [String]$buildOrRun=$(throw "Build (build) or Run (run)? Example: .\make.ps1 stack run")
)

$currentDir = Split-Path $MyInvocation.MyCommand.Path

$buildStack    = 'docker build -f Dockerfile-full -t react-server-full .'
$buildFrontend = 'docker build -f Dockerfile-fe -t react-server-fe .'
$runStack      = 'docker run -p 8080:8080 --name stack --rm react-server-full'
$runFrontend   = 'docker run -p 4000:4000 -it --name fe --rm -v ' + $currentDir + '\src\main\frontend:/app react-server-fe bash'

if ($buildOrRun -eq 'build' -And $stackOrFrontend -eq 'stack') {
    Write-Output $buildStack
    Invoke-Expression $buildStack
} elseif ($buildOrRun -eq 'build' -And $stackOrFrontend -eq 'fe') {
    Write-Output $buildFrontend
    Invoke-Expression $buildFrontend
} elseif ($buildOrRun -eq 'run' -And $stackOrFrontend -eq 'stack') {
    Write-Output $runStack
    Invoke-Expression $runStack
} elseif ($buildOrRun -eq 'run' -And $stackOrFrontend -eq 'fe') {
    Write-Output $runFrontend
    Invoke-Expression $runFrontend
} else {
    Write-Output 'Yolo!'
}
